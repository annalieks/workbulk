import React, {useState} from 'react';
import styles from './styles.module.sass';
import ListView from '../../components/ListView';
import Card from '../../components/Card';

// eslint-disable-next-line no-unused-vars
const workgroups = [
    {
        id: '1',
        name: 'Workgroup 1 Some name is here. It is just long. Very very very very very long',
    }, {
        id: '2',
        name: 'Workgroup 2',
    }, {
        id: '2',
        name: 'Workgroup 3',
    }, {
        id: '2',
        name: 'Workgroup 4',
    }, {
        id: '2',
        name: 'Workgroup 5',
    }, {
        id: '2',
        name: 'Workgroup 5',
    }, {
        id: '2',
        name: 'Workgroup 5',
    }
];

// eslint-disable-next-line no-unused-vars
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

const HomePage = () => {
    const [boardsSelected, setBoardsSelected] = useState(false);
    return (
        <div className={styles.home_container}>
            <div className={styles.controls}>
                <button
                    type={'button'}
                    className={`${styles.button} 
                    ${boardsSelected ? null : styles.selected}`}
                    onClick={() => setBoardsSelected(false)}
                >
                    View workgroups
                </button>
                <button
                    type={'button'}
                    className={`${styles.button} 
                    ${boardsSelected ? styles.selected : null}`}
                    onClick={() => setBoardsSelected(true)}
                >
                    View boards
                </button>
            </div>
            <ListView>
                {boardsSelected
                    // eslint-disable-next-line no-unused-vars
                    ? boards.map((b, i) =>
                        <Card key={`board-${i}`} name={b.name} id={b.id} type={'board'}/>)
                    // eslint-disable-next-line no-unused-vars
                    : workgroups.map((w, i) =>
                        <Card key={`workgroup-${i}`} name={w.name} id={w.id} type={'wg'}/>)
                }
            </ListView>
        </div>
    );
}

export default HomePage;
