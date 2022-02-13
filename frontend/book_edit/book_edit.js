import {getBackendUrl} from "../js/configuration.js";
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load',()=>{
  const editForm = document.getElementById('editForm');
  editForm.addEventListener('submit',event => editBook(event));
  fetchAndDisplayBook();
})

function fetchAndDisplayBook(){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 200){
      displayBook(JSON.parse(this.responseText));
    }
  }
  xhttp.open("GET",getBackendUrl()+'/api/1/authors/'+getParameterByName('author')
    +'/books/'+getParameterByName('book'));
  xhttp.send();
}

function displayBook(response){
  for(const [key, value] of Object.entries(response)){
    let input = document.getElementById(key);
    if(input){
      input.value = value;
    }
  }
}
function editBook(event){
  event.preventDefault();

  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 202){
      window.location.href = '../author_view/author_view.html?author='+getParameterByName('author');
    }
  }
  xhttp.open("PUT",getBackendUrl()+'/api/1/authors/'+getParameterByName('author')+'/books/'
    +getParameterByName('book'),true);
  const request = {
    'title': document.getElementById('title').value,
    'numberOfPages':document.getElementById('numberOfPages').value,
  }
  xhttp.setRequestHeader('Content-type','application/json');
  xhttp.send(JSON.stringify(request));
}
