package com.example.demo.controller;


import com.example.demo.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class v1Controller {

    //part 1
    @GetMapping("/echo/{queryText}")
    public String echoService(@PathVariable(value = "queryText") String queryText){
        return queryText;
    }

    @GetMapping("/issuetypes")
    public List<IssueType> issueTypes(@RequestParam String jiraUrl) throws IOException {
        if(jiraUrl.equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Required String parameter 'jiraUrl'is not present",new InputMismatchException());
            //return;
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url( jiraUrl + "rest/api/2/search?jql=")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();

        //Gson gson = new GsonBuilder().serializeNulls().create();

        IssueList list = new Gson().fromJson(response.body().string(),IssueList.class);

        List<IssueType> issuetypes = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        boolean exists = false;
        for(Issue i : list.getIssues()){
            if(!ids.contains(i.getFields().getIssueType().getId())){
                for(int j : ids){
                    if(j == Integer.parseInt(i.getFields().getIssueType().getId())){
                        exists = true;
                    }
                }
                if(!exists){
                    issuetypes.add(i.getFields().getIssueType());
                    ids.add(Integer.parseInt(i.getFields().getIssueType().getId()));
                }
                exists = false;
            }
        }
        long total = list.getTotal();
        if(total > 50) {
            for (long k = 50; k < total; k += 50) {
                if (k > total) {
                    k = total;
                }

                client = new OkHttpClient().newBuilder()
                        .build();
                request = new Request.Builder()
                        .url(jiraUrl + "rest/api/2/search?jql=&startAt=" + k)
                        .method("GET", null)
                        .build();
                response = client.newCall(request).execute();

                list = new Gson().fromJson(response.body().string(), IssueList.class);

                exists = false;
                for (Issue i : list.getIssues()) {
                    if (!ids.contains(i.getFields().getIssueType().getId())) {
                        for (int j : ids) {
                            if (j == Integer.parseInt(i.getFields().getIssueType().getId())) {
                                exists = true;
                            }
                        }
                        if (!exists) {
                            issuetypes.add(i.getFields().getIssueType());
                            ids.add(Integer.parseInt(i.getFields().getIssueType().getId()));
                        }
                        exists = false;
                    }
                }

            }

        }
        return issuetypes;
    }

    @GetMapping("/issues/subtasks")
    public SubtasksDTO subtasks(@RequestParam String jiraUrl,@RequestParam String projectId) throws IOException {
        if (jiraUrl.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required String parameter 'jiraUrl'is not present", new InputMismatchException());
            //return;
        }
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(jiraUrl + "rest/api/2/search?jql=")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();


        IssueList list = new Gson().fromJson(response.body().string(), IssueList.class);

        List<IssueDTO> issueList = new ArrayList<>();


        List<IssueType> issuetypes = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        boolean exists = false;
        for(Issue i : list.getIssues()){
            //if(!ids.contains(i.getFields().getIssueType().getId())){
                /*for(int j : ids){
                    if(j == Integer.parseInt(i.getFields().getIssueType().getId())){
                        exists = true;
                    }
                }*/
                if(!i.getFields().getIssueType().getSubtask()){
                    exists = true;
                }else if(!i.getKey().contains(projectId)){
                    exists = true;
                }
                if(!exists){
                    //issuetypes.add(i.getFields().getIssueType());

                    FieldDTO temp2 = new FieldDTO(i.getFields().getAssignee(),i.getFields().getReporter(),i.getFields().getProject());
                    /*temp2.setAssignee(i.getFields().getAssignee());
                    temp2.setProject(i.getFields().getProject());
                    temp2.setReporter(i.getFields().getReporter());
                    */IssueDTO temp = new IssueDTO(temp2,i.getId(),i.getKey());
                    //temp.setFields(temp2);
                    issueList.add(temp);
                    //ids.add(Integer.parseInt(i.getFields().getIssueType().getId()));
                }
                exists = false;
            //}
        }
        long total = list.getTotal();
        if(total > 50) {
            for (long k = 50; k < total; k += 50) {
                if (k > total) {
                    k = total;
                }

                client = new OkHttpClient().newBuilder()
                        .build();
                request = new Request.Builder()
                        .url(jiraUrl + "rest/api/2/search?jql=&startAt=" + k)
                        .method("GET", null)
                        .build();
                response = client.newCall(request).execute();

                list = new Gson().fromJson(response.body().string(), IssueList.class);

                exists = false;
                for(Issue i : list.getIssues()){
                   // if(!ids.contains(i.getFields().getIssueType().getId())){
                        /*for(int j : ids){
                            if(j == Integer.parseInt(i.getFields().getIssueType().getId())){
                                exists = true;
                            }
                        }*/
                        if(!i.getFields().getIssueType().getSubtask()){
                            exists = true;
                        }else if(!i.getKey().contains(projectId)){
                            exists = true;
                        }
                        if(!exists){
                            //issuetypes.add(i.getFields().getIssueType());
                            FieldDTO temp2 = new FieldDTO(i.getFields().getAssignee(),i.getFields().getReporter(),i.getFields().getProject());
                            /*temp2.setAssignee(i.getFields().getAssignee());
                            temp2.setProject(i.getFields().getProject());
                            temp2.setReporter(i.getFields().getReporter());
                            */IssueDTO temp = new IssueDTO(temp2,i.getId(),i.getKey());
                            //temp.setFields(temp2);
                            issueList.add(temp);
                            //ids.add(Integer.parseInt(i.getFields().getIssueType().getId()));
                            //ids.add(Integer.parseInt(i.getFields().getIssueType().getId()));
                        }
                        exists = false;
                    //}
                }

            }

        }


        SubtasksDTO result = new SubtasksDTO();

        result.setIssues(issueList);

        return result;
    }

    @PostMapping("/users/find-top-n-users")
    public List<ProjectMemberDTO> findTopN(@RequestParam String jiraUrl,@RequestParam int topn, @RequestBody List<String> ids) throws IOException {




        List<ProjectMemberDTO> members = new ArrayList<>();


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(jiraUrl + "rest/api/2/search?jql=")
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();


        IssueList list = new Gson().fromJson(response.body().string(), IssueList.class);

        boolean exists = false;
        for(Issue i : list.getIssues()){

            if (i.getFields().getAssignee() != null) {
                for (String key : ids) {
                    if (!i.getKey().contains(key)) {
                        exists = true;
                    }
                }
                for (ProjectMemberDTO p : members) {
                    if (i.getFields().getAssignee().getKey().equals(p.getKey())) {
                        exists = true;
                        p.incrementIssueCount();
                    }
                }
                if (!exists) {
                    ProjectMember assignee = i.getFields().getAssignee();

                        ProjectMemberDTO temp = new ProjectMemberDTO(assignee.getName(), assignee.getKey(), assignee.getEmailAddress(), assignee.getDisplayName(), 1);
                        members.add(temp);


                }
                exists = false;
            }
        }

        long total = list.getTotal();
        if(total > 50) {
            for (long k = 50; k < total; k += 50) {
                if (k > total) {
                    k = total;
                }

                client = new OkHttpClient().newBuilder()
                        .build();
                request = new Request.Builder()
                        .url(jiraUrl + "rest/api/2/search?jql=&startAt=" + k)
                        .method("GET", null)
                        .build();
                response = client.newCall(request).execute();

                list = new Gson().fromJson(response.body().string(), IssueList.class);

                exists = false;
                for(Issue i : list.getIssues()) {
                    if (i.getFields().getAssignee() != null) {
                        for (String key : ids) {
                            if (!i.getKey().contains(key)) {
                                exists = true;
                            }
                        }
                        for (ProjectMemberDTO p : members) {
                            if (i.getFields().getAssignee().getKey().equals(p.getKey())) {
                                exists = true;
                                p.incrementIssueCount();
                            }
                        }
                        if (!exists) {
                            ProjectMember assignee = i.getFields().getAssignee();

                            ProjectMemberDTO temp = new ProjectMemberDTO(assignee.getName(), assignee.getKey(), assignee.getEmailAddress(), assignee.getDisplayName(), 1);
                            members.add(temp);

                        }
                        exists = false;
                    }
                }

            }

        }

        Collections.sort(members,(a,b)->{return b.getIssueCount() - a.getIssueCount();});

        List<ProjectMemberDTO> result = new ArrayList<>();

        for(int i = 0 ; i < topn ; i++){
            result.add(members.get(i));
        }

        return result;
    }


    @GetMapping("/findAll")
    public List<IssueList> findall() throws IOException{

        List<IssueList> all = new ArrayList<>();
        for(int i = 0 ; i < 5000 ; i += 50){

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://codemaster.obss.io/jira/rest/api/2/search?jql=&startAt"+i)
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();

            IssueList list = new Gson().fromJson(response.body().string(), IssueList.class);
            all.add(list);
        }





        return all;
    }


}
