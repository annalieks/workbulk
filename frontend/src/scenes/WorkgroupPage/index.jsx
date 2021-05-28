import React, {useEffect} from 'react';
import {useParams} from 'react-router-dom';
import styles from './styles.module.sass';
import Card from "../../components/Card";
import ListView from '../../components/ListView';
import {fetchWorkgroupRoutine} from "./routines";
import {connect} from 'react-redux';

const WorkgroupPage = ({workgroup, fetchWorkgroup}) => {
    // eslint-disable-next-line no-unused-vars
    const params = useParams();
    useEffect(() => {
        fetchWorkgroup(params.id);
    }, [params.id]);
    return (
        <div className={styles.workgroup_container}>
            <div className={styles.title}>
                {workgroup.name}
            </div>
            <ListView>
                {workgroup.boards.map((b, i) =>
                    <Card key={`board-${i}`} name={b.name} id={b.id} type={'board'}/>)}
            </ListView>
        </div>
    );
}

const mapStateToProps = (state) => ({
    workgroup: state.workgroup
});

const mapDispatchToProps = {
    fetchWorkgroup: fetchWorkgroupRoutine
};

export default connect(mapStateToProps, mapDispatchToProps)(WorkgroupPage);
