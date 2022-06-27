import React from 'react';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import NavigationBar from './component/NavigationBar'
import BannerList from './component/BannerList'
import AddBanner from './component/AddBanner'
import CategoryList from './component/CategoryList'
import AddCategory from './component/AddCategory'
import Login from './component/Welcome'

function App() {
  return (
    <Router>
      <NavigationBar/>
      <Container fluid>
        <Row>
          <Col>
            <Routes>
              <Route exact path="/" element={<Login/>} />
              <Route exact path="/banner" element={<BannerList/>} />
              <Route exact path="/banner/addBanner" element={<AddBanner/>} />
              <Route exact path="/category" element={<CategoryList/>} />
              <Route exact path="/category/AddCategory" element={<AddCategory/>} />
            </Routes>
          </Col>
        </Row>
      </Container>
    </Router>
  );
}

export default App;