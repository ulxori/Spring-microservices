import {getBackendUrl} from "../js/configuration.js";
import {
  clearElementChildren,
  createButtonCell,
  createLinkCell,
  createTextCell,
  getParameterByName,
  setTextNode
} from "../js/dom_utils.js";

window.addEventListener('load',()=>{
  fetchAndDisplayAuthor();
  fetchAndDisplayBooks();
  let addBook = document.getElementById('addLink');
  addBook.href = "../book_add/book_add.html?author="+getParameterByName('author');
})

function fetchAndDisplayAuthor(){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 200){
      displayAuthor(JSON.parse(this.responseText));
    }
  }
  xhttp.open("GET",getBackendUrl()+'/api/1/authors/'+getParameterByName('author'),true);
  xhttp.send();
}

function displayAuthor(author){
  setTextNode('name',author.name);
  setTextNode('surname',author.surname);
}

function fetchAndDisplayBooks(){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 200){
      displayBooks(JSON.parse(this.responseText));
    }
  }
  xhttp.open("GET",getBackendUrl()+'/api/1/authors/'+getParameterByName('author')+'/books',true);
  xhttp.send();
}

function displayBooks(books){
  let tableBody = document.getElementById('tableBody');
  clearElementChildren(tableBody);
  books.forEach(book =>{
    tableBody.appendChild(createTableRow(book));
  })
}

function createTableRow(book){
  let tr = document.createElement('tr');
  tr.appendChild(createTextCell(book.title));
  tr.appendChild(createLinkCell('view', '../book_view/book_view.html?author='
    +getParameterByName('author')+'&book='+book.id));
  tr.appendChild(createLinkCell('edit','../book_edit/book_edit.html?author='
    +getParameterByName('author')+'&book='+book.id));
  tr.appendChild(createButtonCell('delete',()=>deleteBook(book.id)));
  return tr;
}

function deleteBook(book){
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function (){
    if(this.readyState === 4 && this.status === 204){
      fetchAndDisplayBooks();
    }
  };
  xhttp.open("DELETE", getBackendUrl()+'/api/1/authors/'+getParameterByName('author')
    +'/books/'+book, true);
  xhttp.send();
}


