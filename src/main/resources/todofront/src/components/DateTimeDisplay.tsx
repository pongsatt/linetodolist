import * as React from 'react';

export interface IProps {
    date: Date | string
}

export default function DateTimeDisplay({date}: IProps) {
    if (typeof date === 'string') {
        date = new Date(date);
    }

    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDay();
    const hour = date.getHours();
    const min = date.getMinutes();

    return (<span style={{fontSize: 'small', padding: '0 5px 0 5px'}}>{day}/{month}/{year} {hour}:{min}</span>);
}