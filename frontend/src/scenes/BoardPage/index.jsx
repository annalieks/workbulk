import React, {useEffect} from 'react';
import {useParams} from 'react-router-dom';
import styles from './styles.module.sass';
import Column from "../../components/Column";
import {addCardRoutine, fetchBoardRoutine} from "./routines";
import {connect} from 'react-redux';
import ScreenLoader from "../../components/ScreenLoader";

const BoardPage = ({board, fetchBoard, addCard}) => {
    const params = useParams();
    useEffect(() => {
        if (params.id) {
            fetchBoard(params.id);
        }
    }, [params]);
    return board.loading ? <ScreenLoader/> : (
        <div className={styles.board_container}>
            <div className={styles.header}>
                <p className={styles.name}>{board.name}</p>
                <p>{board.description}</p>
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
    addCard: addCardRoutine
}

export default connect(mapStateToProps, mapDispatchToProps)(BoardPage);
