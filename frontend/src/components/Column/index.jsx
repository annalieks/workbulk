import React, {useState} from 'react';
import styles from './styles.module.sass';
import TaskCard from '../TaskCard';
import Popup from "../Popup";
import {useParams} from "react-router-dom";

const Column = ({id, name, cards, addCard}) => {
    const [cardName, setCardName] = useState('');
    const [showPopup, setShowPopup] = useState(false);
    const params = useParams();

    const saveCard = () => {
        addCard({id, text: cardName, boardId: params.id});
    };

    return (
        <>
            {showPopup && <Popup
                show={showPopup}
                setShow={setShowPopup}
                name={cardName}
                namePlaceholder={'Text'}
                setName={setCardName}
                onClick={() => {
                    saveCard();
                    setShowPopup(false);
                    setCardName('');
                }}
            />}
            <div className={styles.column_container}>
                <div className={styles.header}>
                    <p className={styles.name}>{name}</p>
                    <button
                        type={'button'}
                        className={styles.plus_icon}
                        onClick={() => setShowPopup(true)}
                    >
                        +
                    </button>
                </div>
                <div className={styles.cards_container}>
                    {cards.map((c, i) => <TaskCard key={`card-${i}`} id={c.id} text={c.text}/>)}
                </div>
            </div>
        </>
    );
}

export default Column;
