import React from 'react';

import {Jumbotron} from 'react-bootstrap';

class Welcome extends React.Component {
    render() {
        return (
            <Jumbotron className="bg-dark text-white text-center">
                <h1>Web application to manage advertising categories and text banners</h1>
            </Jumbotron>
        );
    }
}

export default Welcome