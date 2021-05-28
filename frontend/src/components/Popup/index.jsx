import React from 'react';
import styles from './styles.module.sass';
import {Form, Icon, Input, TextArea} from 'semantic-ui-react';

// eslint-disable-next-line no-unused-vars
const Popup = ({show, setShow, onClick, name, namePlaceholder, setName, description, setDescription}) => {
    return show ? (
        <div className={styles.popup_container}>
            <Form className={styles.form_container}>
                <button
                    className={styles.close_icon}
                    type={'button'}
                    onClick={() => setShow(false)}
                >
                    <Icon name={'x'}/>
                </button>
                <Input
                    placeholder={namePlaceholder || 'Name'}
                    value={name}
                    className={styles.name}
                    onChange={(e) => setName(e.target.value)}
                />
                {description !== undefined && <TextArea
                    value={description}
                    placeholder={'Description'}
                    className={styles.description}
                    onChange={(e) => setDescription(e.target.value)}
                />}
                <div className={styles.button_container}>
                    <button
                        type={'button'}
                        onClick={() => onClick()}
                    >
                        Save
                    </button>
                </div>
            </Form>
        </div>
    ) : null;
}

export default Popup;
