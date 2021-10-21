import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styles from './styles.module.sass';
import Card from "../../components/Card";
import ListView from '../../components/ListView';
import { fetchWorkgroupRoutine, createBoardRoutine, editBoardRoutine, deleteBoardRoutine } from "./routines";
import { connect } from 'react-redux';
import Popup from '../../components/Popup';

const WorkgroupPage = ({ workgroup, fetchWorkgroup, createBoard, editBoard, deleteBoard }) => {
    const params = useParams();
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [showPopup, setShowPopup] = useState(false);
    const [selectedEdit, setSelectedEdit] = useState('');

    const [showEditPopup, setShowEditPopup] = useState(false);
    console.log(showEditPopup)
    useEffect(() => {
        fetchWorkgroup(params.id);
    }, [params.id]);

    const onSave = () => {
        createBoard({
            id: params.id,
            name: name,
            description: description
        });
        setShowPopup(false);
        setName('');
        setDescription('');
    }

    const onEdit = () => {
        editBoard({
            id: selectedEdit,
            workgroupId: params.id,
            name: name,
            description: description,
        });
        setName('');
        setDescription('');
        setSelectedEdit('');
        setShowEditPopup(false);
    }

    return (
        <div className={styles.workgroup_container}>
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
            <div className={styles.header}>
                <div>
                    <div className={styles.title}>
                        {workgroup.name}
                    </div>
                    <div className={styles.description}>
                        {workgroup.description}
                    </div>
                </div>
                <button
                    type={'button'}
                    className={styles.button}
                    onClick={() => setShowPopup(true)}
                >
                    Create Board
                </button>
            </div>
            <ListView>
                {workgroup.boards.map((b, i) =>
                    <Card key={`board-${i}`} name={b.name} id={b.id} type={'board'}
                        onDelete={() => deleteBoard({workgroupId: params.id, id: b.id})}
                        onEdit={() => {
                            setSelectedEdit(b.id);
                            setShowEditPopup(true);
                        }} />)}
            </ListView>
        </div>
    );
}

const mapStateToProps = (state) => ({
    workgroup: state.workgroup
});

const mapDispatchToProps = {
    fetchWorkgroup: fetchWorkgroupRoutine,
    createBoard: createBoardRoutine,
    editBoard: editBoardRoutine,
    deleteBoard: deleteBoardRoutine,
};

export default connect(mapStateToProps, mapDispatchToProps)(WorkgroupPage);
