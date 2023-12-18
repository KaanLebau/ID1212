// Login.js
import '../../assets/styles/Login.css';

function Login() {
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

          <form method="post" action="/">
            <div className="row">
              <label htmlFor="username">Email: </label>
              <input
                type="text"
                placeholder="Username@kth.se"
                id="username"
                name="email"
                required />
            </div>
            <div className="row">
              <label htmlFor="password">Password: </label>
              <input
                type="password"
                placeholder="Password"
                id="password"
                name="password"
                required />
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
