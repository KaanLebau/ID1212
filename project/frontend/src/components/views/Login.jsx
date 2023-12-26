import '../../assets/styles/Login.css';
import { useNavigate } from 'react-router-dom';
import { getUser } from '../../services/ApiService';

function Login() {
    let navigate = useNavigate();
  
    const handleLogin = async (credentials) => {
      event.preventDefault();
      const formData = new FormData(event.target);
      const username = formData.get('username').split('@')[0];
      const password = formData.get('password');

      const user = await getUser(username, password);
  
      if (user && !user.displayName) {
        navigate('/displayname');
      } else {
        navigate('/home');
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
