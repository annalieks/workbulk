import React from 'react';
import styles from './styles.module.sass';

const ListView = (props) => (
    <div className={styles.list_container}>
        {props.children}
    </div>
);

export default ListView;
