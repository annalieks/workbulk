import React from 'react';
import styles from './styles.module.sass';
import {Loader} from 'semantic-ui-react';

const ListView = (props) => (
    <div className={styles.list_container}>
        {props.loading ? <Loader active inline='centered' />: props.children}
    </div>
);

export default ListView;
