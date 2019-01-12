import { ITodo } from './../components/Todo';

function handleError() {
    alert('Error occurred.');
}

export function loadTodos() {
    return fetch('/todos')
    .then(function(response) {
      return response.json();
    })
    .catch(handleError);
}

export function toggleComplete(todo: ITodo) {
    return fetch(`/todos/toggleComplete?id=${todo.id}`, {
        method: 'PUT'
    })
    .then(function(response) {
      return response.json();
    })
    .catch(handleError);
}

export function toggleImportant(todo: ITodo) {
    return fetch(`/todos/toggleImportant?id=${todo.id}`, {
        method: 'PUT'
    })
    .then(function(response) {
      return response.json();
    })
    .catch(handleError);
}