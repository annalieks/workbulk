import React from 'react';
import styles from './styles.module.sass';
import { Link } from "react-router-dom";
import { Icon } from 'semantic-ui-react';

const Card = ({ id, name, type, onDelete, onEdit }) => {
    return (
        <div>
            <Link to={`/${type}/${id}`}>
                <div className={styles.card_container}>
                    <p>{name}</p>
                </div>
            </Link>
            <div className={styles.button_container}>
                <button
                    className={styles.delete_icon}
                    type={'button'}
                    onClick={() => onDelete()}
                >
                    <Icon name={'trash'} />
                </button>
                <div className={styles.controls}>
                    <button
                        className={styles.edit_icon}
                        type={'button'}
                        onClick={() => onEdit()}
                    >
                        <Icon name={'edit'} />
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Card;
