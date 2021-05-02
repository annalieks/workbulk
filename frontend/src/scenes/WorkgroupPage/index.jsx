import React from 'react';
import {useLocation} from 'react-router-dom';
import styles from './styles.module.sass';
import Card from "../../components/Card";
import ListView from "../../components/ListView";

const wg = {
    id: '1',
    name: 'My Workgroup'
}
const boards = [
    {
        id: '1',
        name: 'Board 1',
    }, {
        id: '2',
        name: 'Board 2',
    }, {
        id: '2',
        name: 'Board 3',
    }, {
        id: '2',
        name: 'Board 4',
    }, {
        id: '2',
        name: 'Board 5',
    }
]

const WorkgroupPage = () => {
    // eslint-disable-next-line no-unused-vars
    const location = useLocation();
    return (
        <div className={styles.workgroup_container}>
            <div className={styles.title}>
                {wg.name}
            </div>
            <ListView>
                {boards.map((b, i) =>
                    <Card key={`board-${i}`} name={b.name} id={b.id} type={'board'}/>)}
            </ListView>
        </div>
    );
}

export default WorkgroupPage;
