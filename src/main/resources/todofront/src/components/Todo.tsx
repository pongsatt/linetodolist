import * as React from 'react';
import DateTimeDisplay from './DateTimeDisplay';

export interface ITodo {
    id: string
    task: string
    date: Date
    completed: boolean
    important: boolean
}

export interface ITodoProps {
    todo: ITodo
    onImportantClick: (todo: ITodo) => void
    onCompleteClick: (todo: ITodo) => void
}

export default function Todo({ todo, onImportantClick, onCompleteClick }: ITodoProps) {
    const { task, date, important, completed } = todo;

    const clickImportant = () => onImportantClick(todo);
    const clickComplete = () => onCompleteClick(todo);

    return <div>
        <input type="checkbox" title="Completed" checked={completed} onChange={clickComplete} />
        <span style={{padding: '0 5px 0 5px', textDecoration: completed?'line-through':''}}>{task}</span>
        <DateTimeDisplay date={date}/>
        {important ? 
            <button onClick={clickImportant}>Not important</button> : 
            <button onClick={clickImportant}>Mark as important</button>}
    </div>
}