import {getBackendUrl} from "../js/configuration.js";

window.addEventListener('load',()=>{
  const addForm = document.getElementById('addForm');
  addForm.addEventListener('submit', event=>addAuthor(event));
})

function addAuthor(event){
  event.preventDefault();

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 201){
      window.location.href = '../authors_list/authors.html';
    }
  }
  xhttp.open("POST",getBackendUrl()+'/api/1/authors',true);
  const request = {
    'name': document.getElementById('name').value,
    'surname':document.getElementById('surname').value,
    'alive':document.querySelector('input[name="isAlive"]:checked').value
  }

  xhttp.setRequestHeader('Content-type','application/json');
  xhttp.send(JSON.stringify(request));
}
