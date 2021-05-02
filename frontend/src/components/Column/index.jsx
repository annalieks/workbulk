import React from 'react';
import styles from './styles.module.sass';
import TaskCard from "../TaskCard";

const Column = ({id, name, cards}) => {
    return (
        <div className={styles.column_container}>
            {cards.map(c => <TaskCard id={c.id} name={c.name}/>)}
        </div>
    );
}

export default Column;
