package com.example.firstinternrepository.TasksControllerTest;

import com.example.firstinternrepository.dto.CreateTaskDTO;
import com.example.firstinternrepository.dto.UpdateTaskDTO;
import com.example.firstinternrepository.dto.ViewTasksDTO;
import com.example.firstinternrepository.model.Task;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TasksControllerTests {

    private static final String BASE_URI = "http://localhost:8080/tasks";

    @Test
    public void CreateTask() {
        String idOfUser = "68594dd7fb8ac621ee3a9f99";
        CreateTaskDTO createTaskRequest = new CreateTaskDTO(idOfUser, "new Title", "new Description");


        Task createdResponse = given()
                .baseUri(BASE_URI)
                .basePath("/new-task")
                .contentType(ContentType.JSON)
                .body(createTaskRequest)
                .when().post()
                .then().statusCode(201)
                .extract().as(Task.class);

        assertThat(createdResponse).isNotNull();
        assertThat(createdResponse.getTitle()).isEqualTo("new Title");
        assertThat(createdResponse.getDescription()).isEqualTo("new Description");
        assertThat(createdResponse.getHandler()).isNotNull();
        assertThat(createdResponse.getHandler().getId()).isEqualTo(idOfUser);

        System.out.println("Created task: " + createdResponse);
    }

//    @Test
    public void GetAllTasks(){
        String idUser = "68594dd7fb8ac621ee3a9f99";

        List<ViewTasksDTO> response = given()
                .basePath(BASE_URI)
                .param("userId", idUser)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<ViewTasksDTO>>() {});

        assertThat(response).isNotNull();

        for (ViewTasksDTO task : response){
            assertThat(task).isNotNull();
        }
    }

    @Test
    public void UpdateTitleTask() {
        String taskId = "68592a07152bde5eed194f81";
        String newTitle = "Updated Title";

        Task updatedTask = given()
                .baseUri(BASE_URI)
                .basePath("/update-title/" + taskId)
                .contentType(ContentType.JSON)
                .body("{ \"title\": \"" + newTitle + "\" }")
                .when().patch()
                .then().statusCode(200)
                .extract().as(Task.class);

        assertThat(updatedTask.getId()).isEqualTo(taskId);
        assertThat(updatedTask.getTitle()).isEqualTo(newTitle);
    }


    @Test
    public void UpdateDescription() {
        String taskId = "68592a07152bde5eed194f81";
        String newDescription = "Updated Description";

        Task updatedTask = given()
                .baseUri(BASE_URI)
                .basePath("/update-description/" + taskId)
                .contentType(ContentType.JSON)
                .body("{ \"description\": \"" + newDescription + "\" }")
                .when().patch()
                .then().statusCode(200)
                .extract().as(Task.class);

        assertThat(updatedTask.getId()).isEqualTo(taskId);
        assertThat(updatedTask.getDescription()).isEqualTo(newDescription);
    }


    @Test
    public void UpdateTask() {
        String taskId = "68595c57184cfa7294d8cad7";

        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO("Completely New Title", "Completely New Description");

        Task updatedTask = given()
                .baseUri(BASE_URI)
                .basePath("/update-task/" + taskId)
                .contentType(ContentType.JSON)
                .body(updateTaskDTO)
                .when().patch()
                .then().statusCode(200)
                .extract().as(Task.class);

        assertThat(updatedTask.getTitle()).isEqualTo("Completely New Title");
        assertThat(updatedTask.getDescription()).isEqualTo("Completely New Description");
    }


    @Test
    public void DeleteTask() {
        String taskId = "68594d81fb8ac621ee3a9f98";

        String responseMessage = given()
                .baseUri(BASE_URI)
                .basePath("/delete/" + taskId)
                .when().delete()
                .then().statusCode(200)
                .extract().asString();

        assertThat(responseMessage).contains("Successfully deleted");
    }

}
