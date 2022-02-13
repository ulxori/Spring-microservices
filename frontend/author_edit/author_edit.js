import {getBackendUrl} from "../js/configuration.js";
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load',()=>{
  const editForm = document.getElementById('editForm');
  editForm.addEventListener('submit',event => editAuthor(event));
  fetchAndDisplayAuthor();
})

function fetchAndDisplayAuthor(){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 200){
      displayAuthor(JSON.parse(this.responseText));
    }
  }
  xhttp.open("GET",getBackendUrl()+'/api/1/authors/'+getParameterByName('author'));
  xhttp.send();
}

function displayAuthor(response){
  console.log(response);
  for(const [key, value] of Object.entries(response)){
    if(key === 'alive'){
      if(value === true){
        document.getElementById('yes').checked = true;
      }else{
        document.getElementById('no').checked = true;
      }
    }else {
      let input = document.getElementById(key);
      if(input){
        input.value = value;
      }
    }
  }
}
function editAuthor(event){
  event.preventDefault();

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 202){
      window.location.href = '../authors_list/authors.html';
    }
  }
  xhttp.open("PUT",getBackendUrl()+'/api/1/authors/'+getParameterByName('author'),true);
  const request = {
    'name': document.getElementById('name').value,
    'surname':document.getElementById('surname').value,
    'alive':document.querySelector('input[name="isAlive"]:checked').value
  }
  xhttp.setRequestHeader('Content-type','application/json');
  xhttp.send(JSON.stringify(request));
}
