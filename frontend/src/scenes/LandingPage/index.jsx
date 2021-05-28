import React from 'react';
import descriptionImage from '../../assets/description.svg';
import featuresImage from '../../assets/features.svg';
import styles from './styles.module.sass';

const data = [
    {
        title: 'Everything is easy with Workbulk',
        description: 'Great planning - great result. And Workbulk will help you with it!',
        image: descriptionImage
    },
    {
        title: 'Pay attention only to important things',
        description: 'Workbulk will care about the rest for you.',
        image: featuresImage
    }
]

const LandingPage = () => (
    <div className={styles.landing_container}>
        {data.map((item, i) => (
            <div
                key={`block-${i}`}
                className={styles.feature_container}
                style={{flexDirection: i % 2 ? 'row-reverse' : 'row'}}
            >
                <div className={styles.text}>
                    <div className={styles.title}>{item.title}</div>
                    <div className={styles.description}>{item.description}</div>
                </div>
                <div className={styles.image_container}>
                    <img src={item.image} alt={'Description'}/>
                </div>
            </div>
        ))}
    </div>
);

export default LandingPage;
