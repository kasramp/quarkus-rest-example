package com.madadipouya.quarkus.example.controller;

import com.madadipouya.quarkus.example.entity.User;
import com.madadipouya.quarkus.example.exception.UserNotFoundException;
import com.madadipouya.quarkus.example.exceptionhandler.ExceptionHandler;
import com.madadipouya.quarkus.example.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Operation(summary = "Gets users", description = "Lists all available users")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))))
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Gets a user", description = "Retrieves a user by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description="User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public User getUser(@PathParam("id") int id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @POST
    @Operation(summary = "Adds a user", description = "Creates a user and persists into database")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))))
    public User createUser(@Valid UserDto userDto) {
        return userService.saveUser(userDto.toUser());
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Updates a user", description = "Updates an existing user by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description="User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public User updateUser(@PathParam("id") int id, @Valid UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(id, userDto.toUser());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a user", description = "Deletes a user by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Success"),
            @APIResponse(responseCode = "404", description="User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class)))
    })
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Schema(name="UserDTO", description="User representation to create")
    public static class UserDto {

        @NotBlank
        @Schema(title="User given name", required = true)
        private String firstName;

        @NotBlank
        @Schema(title="User surname", required = true)
        private String lastName;

        @Min(value = 1, message = "The value must be more than 0")
        @Max(value = 200, message = "The value must be less than 200")
        @Schema(title="User age between 1 to 200", required = true)
        private int age;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public User toUser() {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAge(age);
            return user;
        }
    }
}
