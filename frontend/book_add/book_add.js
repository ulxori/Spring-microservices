import {getBackendUrl} from "../js/configuration.js";
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load',()=>{
  const addForm = document.getElementById('addForm');
  addForm.addEventListener('submit', event=>addBook(event));
})

function addBook(event){
  console.log('hello')
  event.preventDefault();

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 201){
      window.location.href = '../author_view/author_view.html?author='+getParameterByName('author');
    }
  }
  xhttp.open("POST",getBackendUrl()+'/api/1/authors/'+getParameterByName('author')+'/books',true);
  const request = {
    'title': document.getElementById('title').value,
    'numberOfPages':document.getElementById('numberOfPages').value
  }

  xhttp.setRequestHeader('Content-type','application/json');
  xhttp.send(JSON.stringify(request));
}
