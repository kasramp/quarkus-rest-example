package com.madadipouya.quarkus.example.controller;

import com.madadipouya.quarkus.example.entity.User;
import com.madadipouya.quarkus.example.exception.UserNotFoundException;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private static final SortedSet<User> dummyUsers = new TreeSet<>();

    static {
        dummyUsers.addAll(Set.of(
                createDummyUser(1, "Leonardo", "DiCaprio", 45),
                createDummyUser(2, "Will", "Smith", 51),
                createDummyUser(3, "Denzel", "Washington", 65))
        );
    }

    @GET
    public Set<User> getUsers() {
        return dummyUsers;
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id) throws UserNotFoundException {
        return getUserById(id);
    }

    @POST
    public User createUser(@Valid UserDto userDto) {
        User user = createDummyUser(dummyUsers.last().getId() + 1, userDto.firstName, userDto.lastName, userDto.age);
        dummyUsers.add(user);
        return user;
    }

    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") int id, @Valid UserDto userDto) throws UserNotFoundException {
        User user = getUserById(id);
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);
        user.setAge(userDto.age);
        return user;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        dummyUsers.remove(getUserById(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private User getUserById(int id) throws UserNotFoundException {
        return dummyUsers.stream().filter(user -> user.getId() == id).findFirst()
                .orElseThrow(() -> new UserNotFoundException("The user doesn't exist"));
    }

    private static User createDummyUser(int id, String firstName, String lastName, int age) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        return user;
    }

    public static class UserDto {

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @Min(value = 1, message = "The value must be more than 0")
        @Max(value = 200, message = "The value must be less than 200")
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
    }
}
