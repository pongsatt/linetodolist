import * as React from 'react';
import Todo, { ITodo } from '../components/Todo';
import * as api from '../api/todoApi';
import Loading from '../components/Loading';

export function Todos() {
    const [todos, setTodos] = React.useState<ITodo[]>([]);
    const [loading, setLoading] = React.useState<boolean>(false);

    const showLoading = () => setLoading(true);
    const hideLoading = () => setLoading(false);

    React.useEffect(() => {
        showLoading();
        api.loadTodos()
            .then(list => {
                hideLoading();
                setTodos(list);
            });
    }, []);

    const updateTodos = (todo: ITodo) => {
        hideLoading();
        setTodos(prevTodos => {
            return prevTodos.map(t => {
                if (t.id === todo.id) {
                    return todo;
                }
                return t;
            })
        })
    }

    const importants = todos.filter(t => t.important);
    const unimportants = todos.filter(t => !t.important);

    const onImportantClick = (todo: ITodo) => {
        showLoading();
        api.toggleImportant(todo)
        .then(todoResult => {
            updateTodos(todoResult);
        });
        
    };
    const onCompleteClick = (todo: ITodo) => {
        showLoading();
        api.toggleComplete(todo)
        .then(todoResult => {
            updateTodos(todoResult);
        });
    };

    return (<div>
        <h2>Todo List</h2>
        <h3>Importants</h3>
        {importants.map(todo => <Todo onImportantClick={onImportantClick} onCompleteClick={onCompleteClick} key={todo.id} todo={todo} />)}
        {!importants.length && <p>No important todo</p>}
        <br/>
        <h3>Todos</h3>
        {unimportants.map(todo => <Todo onImportantClick={onImportantClick} onCompleteClick={onCompleteClick} key={todo.id} todo={todo} />)}
        {!unimportants.length && <p>No todo</p>}
        {loading && <Loading/>}
    </div>);
}