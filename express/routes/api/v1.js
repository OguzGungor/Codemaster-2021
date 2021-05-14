var express = require('express');
var router = express.Router();


var axios = require('axios');



router.get('/echo/:queryText', function(req, res, next) {
    res.send(req.params.queryText);
});


router.get("/issuetypes", (req,res,next)=>{

    var config = {
        method: 'get',
        url: `${req.query.jiraUrl}rest/api/2/search?jql=`,
        headers: { }
      };
      
      var list = [];

      
      var check = [];
      axios(config)
      .then(function (response) {

        for(var i = 0 ; i < response.data.total/50 ; i++ ){
            check.push(false);
        }
        response.data.issues.map((issue)=>{
            if(!list.some(i => i.id == issue.fields.issuetype.id)){                
                list.push({id:issue.fields.issuetype.id,
                    description:issue.fields.issuetype.description,
                    name:issue.fields.issuetype.name,
                    subtask: issue.fields.issuetype.subtask});
            }
        })

        check[0] = true;
        
        for(var i = 50 ; i < response.data.total ; i += 50){
            config = {
                method: 'get',
                url: `${req.query.jiraUrl}rest/api/2/search?jql=&&startAt=${i}`,
                headers: { }
            };    
            axios(config).then((response2)=>{

                response2.data.issues.map((issue)=>{
                    if(!list.some(is => is.id == issue.fields.issuetype.id)){                
                        list.push({id:issue.fields.issuetype.id,
                            description:issue.fields.issuetype.description,
                            name:issue.fields.issuetype.name,
                            subtask: issue.fields.issuetype.subtask});
                    }
                })
                check[i] = true;
            })
        }
            

        }).then(()=>{
            while(check.some(ch => !ch)){

            }
            res.send(list);
        })
      .catch(function (error) {
        console.log(error);
        res.send("nope");
      });

      
      //
      
    //res.send(req.query.jiraUrl);
})
  
  module.exports = router;