import React, {Component} from 'react';

import {Card, Form, Button} from 'react-bootstrap';

import axios from 'axios';

export default class AddCategory extends Component {

  constructor(props) {
    super(props);
    this.state = this.initialState;
    this.categoryChange = this.categoryChange.bind(this);
    this.submitCategory = this.submitCategory.bind(this);
  }

  initialState = {
    name:'', request:''
  }

  submitCategory = event => {
    event.preventDefault();

    const category = {
      name: this.state.name,
      request: this.state.request
    };

    axios.post("http://localhost:8080/category/addCategory/", category, {
      auth: {
        username: 'user',
        password: 'password'
      }
    })
        .then(response => {
          if(response.data != null) {
            this.setState(this.initialState);
            alert("Category added to CategoryList");
          }
        });
  }

  categoryChange = event => {
    this.setState({
      [event.target.name]:event.target.value
    });
  }

  resetCategory = () => {
    this.setState(() => this.initialState);
  }

  render() {
    const {name, request} = this.state;

    return (
        <Card className={"border border-dark bg-dark text-white"} style={{ width: '30rem' }}>
          <Card.Header>Add new Category</Card.Header>
          <Form onSubmit={this.submitCategory} onReset={this.resetCategory} id="categoryFormId">
            <Card.Body>
              <Form.Group controlId="formCategoryName">
                <Form.Label>Category name</Form.Label>
                <Form.Control autoComplete="off"
                              name="name"
                              type="test"
                              value={name}
                              onChange={this.categoryChange}
                              className={"bg-dark text-white"}
                              placeholder="Enter category name"
                />
              </Form.Group>

              <Form.Group controlId="formCategoryName">
                <Form.Label>Request ID</Form.Label>
                <Form.Control autoComplete="off"
                              name="request"
                              type="test"
                              value={request}
                              onChange={this.categoryChange}
                              className={"bg-dark text-white"}
                              placeholder="Enter request ID"
                />
              </Form.Group>
            </Card.Body>

            <Card.Footer style={{"textAlign":"right"}}>
              <Button variant="secondary" href="/category">Go back</Button>{' '}
              <Button variant="success" type="submit">Submit</Button>{' '}
              <Button variant="info" type="reset">Reset</Button>
            </Card.Footer>
          </Form>
        </Card>
    );
  }
}