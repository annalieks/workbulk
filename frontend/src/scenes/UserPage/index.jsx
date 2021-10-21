import React, { useState, useEffect } from 'react';
import callWebApi, { serverAddress } from '../../helpers/web.helper';
import styles from './styles.module.sass';
import { InputFile } from 'semantic-ui-react-input-file'
import userLogo from '../../assets/user.jpg';
import { Image } from 'semantic-ui-react'

const UserPage = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        callWebApi({
            endpoint: `/user/me`,
            type: 'GET'
        }).then(r => r.json())
            .then(r => {
                console.log(r);
                setUser(r);
            });

    }, []);

    const onFileChange = (e) => {
        callWebApi({
            endpoint: `/user/${user.id}/image`,
            type: 'POST',
            attachment: e.target.files[0]
        }).then(r => r.text()).then(r => setUser({ ...user, avatar: r }));
    }

    return (
        <div className={styles.user_container}>
            <div className={styles.avatar}>
                {user?.avatar ? <Image src={`${serverAddress}/images/${user?.avatar}`} size='medium' bordered /> :
                    <Image src={userLogo} size='medium' bordered />}
            </div>
            <InputFile
                input={{
                    id: 'input-control-id',
                    onChange: (e) => onFileChange(e)
                }}
            />
            <div className={styles.name}>{user?.firstName || ''} {user?.secondName || ''}</div>
            <div className={styles.email}>{user?.email || ''}</div>
        </div>
    );
}

export default UserPage;