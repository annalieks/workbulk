import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import styles from './styles.module.sass';
import Column from "../../components/Column";
import { addCardRoutine, createColumnRoutine, fetchBoardRoutine } from "./routines";
import { connect } from 'react-redux';
import ScreenLoader from "../../components/ScreenLoader";
import Popup from '../../components/Popup';

const BoardPage = ({ board, fetchBoard, addCard, createColumn }) => {
    const params = useParams();
    const [name, setName] = useState('');
    const [showPopup, setShowPopup] = useState(false);
    useEffect(() => {
        if (params.id) {
            fetchBoard(params.id);
        }
    }, [params]);

    const onSave = () => {
        createColumn({
            id: params.id,
            name: name
        });
        setShowPopup(false);
        setName('');
    }

    return board.loading ? <ScreenLoader /> : (
        <div className={styles.board_container}>
             <Popup
                name={name}
                setName={setName}
                show={showPopup}
                setShow={setShowPopup}
                onClick={() => onSave()}
            />
            <div className={styles.header}>
                <div>
                    <div className={styles.title}>
                        {board.name}
                    </div>
                    <div className={styles.description}>
                        {board.description}
                    </div>
                </div>
                <button
                    type={'button'}
                    className={styles.button}
                    onClick={() => setShowPopup(true)}
                >
                    Create Column
                </button>
            </div>
            <div className={styles.columns_container}>
                {board.columns.map((c, i) =>
                    <Column
                        key={`col-${i}`}
                        id={c.id}
                        name={c.name}
                        cards={c.cards}
                        addCard={addCard}
                    />)}
            </div>
        </div>
    );
}

const mapStateToProps = (state) => ({
    board: state.board,
});

const mapDispatchToProps = {
    fetchBoard: fetchBoardRoutine,
    addCard: addCardRoutine,
    createColumn: createColumnRoutine
}

export default connect(mapStateToProps, mapDispatchToProps)(BoardPage);
