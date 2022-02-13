export function clearElementChildren(element){
  while(element.firstChild){
    element.removeChild(element.firstChild);
  }
}

export function createLinkCell(text, url){
  const a = document.createElement('a');
  const td = document.createElement('td');
  a.href = url;
  a.appendChild(document.createTextNode(text));
  td.appendChild(a);
  return td;
}

export function createButtonCell(text, action){
  const button = document.createElement('button');
  const td = document.createElement('td');
  button.appendChild(document.createTextNode(text));
  button.addEventListener('click',action);
  td.appendChild(button);
  return td;
}

export function createTextCell(text){
  const td = document.createElement('td');
  td.appendChild(document.createTextNode(text));
  return td;
}

export function setTextNode(id, text){
  let element = document.getElementById(id);
  clearElementChildren(element);
  element.appendChild(document.createTextNode(text));
}

export function getParameterByName(name){
  return new URLSearchParams(window.location.search).get(name);
}
