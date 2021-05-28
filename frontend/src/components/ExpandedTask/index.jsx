import React from 'react';
import styles from './styles.module.sass';

const ExpandedTaskCard = ({card}) => {
    return (
        <div className={styles.card_container}>
            <p>{card.name}</p>
        </div>
    );
}

export default ExpandedTaskCard;
