import * as React from 'react';
import Moment from 'react-moment';

export interface IProps {
    date: Date | string
}

export default function DateTimeDisplay({date}: IProps) {
    return (
    <span style={{fontSize: 'small', padding: '0 5px 0 5px'}}>
        <Moment format="DD/MM/YY HH:mm" date={date} />
    </span>);
}