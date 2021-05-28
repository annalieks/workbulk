import React from 'react'
import {Dimmer, Loader} from 'semantic-ui-react'

const ScreenLoader = () => (
    <div>
        <Dimmer active inverted>
            <Loader inverted/>
        </Dimmer>
    </div>
)

export default ScreenLoader
