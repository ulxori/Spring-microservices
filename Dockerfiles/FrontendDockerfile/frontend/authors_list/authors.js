import {getBackendUrl} from '../js/configuration.js';
import {clearElementChildren, createButtonCell, createLinkCell, createTextCell} from "../js/dom_utils.js";

window.addEventListener('load', ()=>{
  fetchAndDisplayAuthors();
});

function fetchAndDisplayAuthors(){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function(){
    if(this.readyState === 4 && this.status === 200){
      displayAuthors(JSON.parse(this.responseText));
    }
  }
  xhttp.open("GET", getBackendUrl() + '/api/1/authors', true);
  xhttp.send();
}

function displayAuthors(authors){
  let tableBody = document.getElementById("tableBody");
  clearElementChildren(tableBody);
  authors.forEach(author => {
    tableBody.appendChild(createTableRow(author));
  });
}

function createTableRow(author){
  let tr = document.createElement('tr');
  tr.appendChild(createTextCell(author.name));
  tr.appendChild(createTextCell(author.surname));
  tr.appendChild(createLinkCell('view','../author_view/author_view.html?author='+author.id));
  tr.appendChild(createLinkCell('edit','../author_edit/author_edit.html?author='+author.id));
  tr.appendChild(createButtonCell('delete', ()=>deleteAuthor(author)));
  return tr;
}

function deleteAuthor(author){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 204){
      fetchAndDisplayAuthors();
    }
  };
  xhttp.open("DELETE", getBackendUrl()+'/api/1/authors/'+author.id, true);
  xhttp.send();
}
