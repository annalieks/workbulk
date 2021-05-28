import React, {useEffect, useState} from 'react';
import styles from './styles.module.sass';
import ListView from '../../components/ListView';
import Card from '../../components/Card';
import {connect} from "react-redux";
import {createBoardRoutine, createWorkgroupRoutine, fetchBoardsRoutine, fetchWorkgroupsRoutine} from "./routines";
import Popup from "../../components/Popup";

const HomePage = ({
                      boardsLoading,
                      workgroupsLoading,
                      boards,
                      workgroups,
                      fetchBoards,
                      fetchWorkgroups,
                      createWorkgroup,
                      createBoard
                  }) => {
    const [boardsSelected, setBoardsSelected] = useState(false);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [showPopup, setShowPopup] = useState(false);

    const onSave = () => {
        setName('');
        setDescription('');
        setShowPopup(false);
        if (boardsSelected) {
            createBoard({name, description});
        } else {
            createWorkgroup({name, description});
        }
    }
    useEffect(() => {
        if (boardsSelected) {
            fetchBoards();
        } else {
            fetchWorkgroups();
        }
    }, [boardsSelected]);

    return (
        <div className={styles.home_container}>
            <Popup
                name={name}
                setName={setName}
                description={description}
                setDescription={setDescription}
                show={showPopup}
                setShow={setShowPopup}
                onClick={() => onSave()}
            />
            <div className={styles.controls}>
                <div>
                    <button
                        type={'button'}
                        className={`${styles.button} 
                    ${styles.blue_button}
                    ${boardsSelected ? null : styles.selected}`}
                        onClick={() => setBoardsSelected(false)}
                    >
                        View workgroups
                    </button>
                    <button
                        type={'button'}
                        className={`${styles.button} 
                    ${styles.blue_button}
                    ${boardsSelected ? styles.selected : null}`}
                        onClick={() => setBoardsSelected(true)}
                    >
                        View boards
                    </button>
                </div>
                <button
                    type={'button'}
                    className={`${styles.button}
                    ${styles.red_button}`}
                    onClick={() => setShowPopup(true)}
                >
                    Create {boardsSelected ? 'board' : 'workgroup'}
                </button>
            </div>
            <ListView loading={boardsSelected ? boardsLoading : workgroupsLoading}>
                {boardsSelected
                    ? boards.map((b, i) =>
                        <Card key={`board-${i}`} name={b.name} id={b.id} type={'board'}/>)
                    : workgroups.map((w, i) =>
                        <Card key={`workgroup-${i}`} name={w.name} id={w.id} type={'wg'}/>)
                }
            </ListView>
        </div>
    );
}

const mapStateToProps = (state) => ({
    boardsLoading: state.home.boardsLoading,
    workgroupsLoading: state.home.workgroupsLoading,
    boards: state.home.boards,
    workgroups: state.home.workgroups,
});

const mapDispatchToProps = {
    fetchWorkgroups: fetchWorkgroupsRoutine,
    fetchBoards: fetchBoardsRoutine,
    createWorkgroup: createWorkgroupRoutine,
    createBoard: createBoardRoutine
}

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
