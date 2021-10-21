import React, { useEffect, useState } from 'react';
import styles from './styles.module.sass';
import ListView from '../../components/ListView';
import Card from '../../components/Card';
import { connect } from "react-redux";
import {
    createBoardRoutine, createWorkgroupRoutine, deleteBoardRoutine, editBoardRoutine, fetchBoardsRoutine, fetchWorkgroupsRoutine,
    deleteWorkgroupRoutine, editWorkgroupRoutine
} from "./routines";
import Popup from "../../components/Popup";
const HomePage = ({
    boardsLoading,
    workgroupsLoading,
    boards,
    workgroups,
    fetchBoards,
    fetchWorkgroups,
    createWorkgroup,
    createBoard,
    deleteWorkgroup,
    editWorkgroup,
    deleteBoard,
    editBoard,
}) => {
    const [boardsSelected, setBoardsSelected] = useState(false);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [showPopup, setShowPopup] = useState(false);
    const [showEditPopup, setShowEditPopup] = useState(false);
    const [selectedEdit, setSelectedEdit] = useState('');

    const onSave = () => {
        if (boardsSelected) {
            createBoard({ name, description });
        } else {
            createWorkgroup({ name, description });
        }
        setName('');
        setDescription('');
        setShowPopup(false);
        setSelectedEdit('');
    }

    const onDeleteWorkgroup = (id) => {
        deleteWorkgroup({
            id
        });
    }

    const onEdit = () => {
        if (boardsSelected) {
            editBoard({
                id: selectedEdit,
                name: name,
                description: description
            });
        } else {
            editWorkgroup({
                id: selectedEdit,
                name: name,
                description: description
            })
        }
        setSelectedEdit('');
        setShowEditPopup(false);
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
            <Popup
                name={name}
                setName={setName}
                description={description}
                setDescription={setDescription}
                show={showEditPopup}
                setShow={setShowEditPopup}
                onClick={() => onEdit()}
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
                        <Card key={`board-${i}`} name={b.name} id={b.id} type={'board'} onDelete={() => deleteBoard({id: b.id})}
                            onEdit={() => {
                                setSelectedEdit(b.id);
                                setName(b.name);
                                setDescription(b.description);
                                setShowEditPopup(true);
                            }} />)
                    : workgroups.map((w, i) =>
                        <Card key={`workgroup-${i}`} name={w.name} id={w.id} type={'wg'} onDelete={() => onDeleteWorkgroup(w.id)
                        } onEdit={() => {
                            setShowEditPopup(true);
                            setName(w.name);
                            setDescription(w.description);
                            setSelectedEdit(w.id);
                        }} />)
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
    createBoard: createBoardRoutine,
    deleteWorkgroup: deleteWorkgroupRoutine,
    editWorkgroup: editWorkgroupRoutine,
    deleteBoard: deleteBoardRoutine,
    editBoard: editBoardRoutine,
}

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
