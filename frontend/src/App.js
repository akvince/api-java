import React, { Component } from 'react';

import './App.css';

class App extends Component {
  state = {
    clients: []
  };

  async componentDidMount() {
    const response = await fetch('/api/brands');
    const body = await response.json();
    this.setState({clients: body});
  }

  render() {
    const {clients} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Product</h2>
              {clients.map(client =>
                  <div key={client.uuid}>
                    {client.name} ({client.price})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}

export default App;
