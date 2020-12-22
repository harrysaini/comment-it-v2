import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect,
} from "react-router-dom";
import PrivateRoute from './_shared-components/PrivateRoute';
import Comments from './pages/comment/Comment';
import Login from './pages/login/Login';
import { AuthService } from './services/auth.service';
import LoadingSpinner from './_shared-components/LoadingSpinner';

interface Props {
}
interface State {
  user: any;
  isLoaded: boolean;
}
class App extends React.Component<Props, State> {

  constructor(props: Props) {
    super(props);
    this.state = {
      user: null,
      isLoaded: false
    }
  }

  componentDidMount = async () => {
    const user = await AuthService.authenticate();
    this.setState({
      user: user,
      isLoaded: true
    });
  }

  render() {

    return(
      <Router>
        <header className="App-header">
          <Link to= '/'><div className="logo">Comment It</div></Link>
          { this.state.isLoaded &&
            <div className="header-btns">
              {  this.state.user && this.state.user.id ?
              ( <div className="user-buttons">
                  <button
                    type="button"
                    className="btn btn-outline-secondary"
                    onClick={() => AuthService.logout()}
                    >Logout</button>
                </div>
              ) : (
                <Link to='/login'>
                  <button type="button" className="btn btn-outline-primary">Login</button>
                </Link>
              )
              }
            </div>
          }
        </header>
      <div className='container'>
          {this.subRender()}
      </div>
    </Router>
    )
  }

  // render inner content of wrapper
  subRender(){

    if(!this.state.isLoaded) {
      return (
        <LoadingSpinner />
      );
    }

    return (
        <div className="App">
          <Switch>
            <Route exact path='/'>
              <div className="index-wrapper">
                <div className="text-center">
                  <br></br><br/><br/>
                  <h6>Comment It</h6>
                  <p>Just comment you feeling, and let it out.</p>
                  <br></br>
                  <Link to='/comments'>
                    <button type="button" className="btn btn-primary">
                        Start Commenting
                    </button>
                  </Link>

                </div>
              </div>
            </Route>
            <PrivateRoute exact path="/comments">
              <Comments />
            </PrivateRoute>
            <Route exact path="/login">
              <Login />
            </Route>
            <Redirect from='*' to='/' />
          </Switch>
        </div>
    );
  }
}

export default App;
