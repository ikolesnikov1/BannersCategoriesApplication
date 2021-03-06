import React, {Component} from 'react';
import axios from 'axios';

import {Card, Table, Button, ButtonGroup} from 'react-bootstrap';

export default class CategoryList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      categories : []
    };
  }

  componentDidMount() {
    axios.get("http://localhost:8080/category/", {
      auth: {
        username: 'user',
        password: 'password'
      }
    })
          .then(response => response.data)
          .then((data) => {
            this.setState({categories: data})
          });
  }

  render() {
    return (
      <Card className={"border border-dark bg-dark text-white"}>
        <Card.Header>Category List</Card.Header>
        <Card.Body>
          <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>Name</th>
                <th>Request id</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {
                this.state.categories.length === 0 ?
                <tr align="center">
                  <td colSpan="6">Categories Available.</td>
                </tr> :
                this.state.categories.map((category) => (
                <tr key={category.id}>
                  <td>{category.name}</td>
                  <td>{category.request}</td>
                  <td>
                    <ButtonGroup>
                      <Button size="sm" variant="primary">Edit</Button>{' '}
                      <Button size="sm" variant="danger">Delete</Button>
                    </ButtonGroup>
                  </td>
                </tr>
                ))
              }
            </tbody>
          </Table>
          <Button variant="primary" href="/category/addCategory">Add new Category</Button>
        </Card.Body>
      </Card>
    );
  }
}
