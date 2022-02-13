import {getParameterByName, setTextNode} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/configuration.js";

window.addEventListener('load',()=>{
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
    +'/books/'+getParameterByName('book'),true);
  xhttp.send();
}

function displayBook(book){
  console.log(book);
  setTextNode('title',book.title);
  setTextNode('numberOfPages',book.numberOfPages);
}
