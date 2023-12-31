import '../../assets/styles/Login.css';
import { useNavigate } from 'react-router-dom';
import { useUserContext } from '../../services/UserContext';
import { useState } from "react";

function Login() {
    let navigate = useNavigate();
    const { loginUser } = useUserContext();
    const [error, setError] = useState('');
  
    const handleLogin = async (event) => {
      event.preventDefault();
      const formData = new FormData(event.target);
      const username = formData.get('username').split('@')[0];
      const password = formData.get('password');

      const user = await loginUser(username, password);
  
      if (user && !user.displayName) {
        console.log(user);
        navigate('/displayname');
      } else if (user) {
        navigate('/home');
      } else {
        setError('Invalid username or password');
      }
    };

  return (
    <div className="login">
      <div className="main">
        <div className="logo">
          <p>KTH-Forum</p>
        </div>
        <div className="login-container">
          <div className="login-message">
            <p>Login using your KTH-account</p>
          </div>
          <div>
            {error && <p className="login-error">{error}</p>}
          </div>
          <form onSubmit={handleLogin}>
            <div className="row">
              <label htmlFor="username">Username: </label>
              <input
                type="username"
                placeholder="Username"
                id="username"
                name="username"
                required
              />
            </div>
            <div className="row">
              <label htmlFor="password">Password: </label>
              <input
                type="password"
                placeholder="Password"
                id="password"
                name="password"
                required
              />
            </div>
            <div className="row">
              <button type="submit">Login</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
