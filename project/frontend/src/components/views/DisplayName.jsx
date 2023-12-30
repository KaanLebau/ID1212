import { useNavigate } from 'react-router-dom';
import { setUserDisplayName } from '../../services/ApiService';
import '../../assets/styles/Displayname.css';
import { useUserContext } from "../../services/UserContext";

function DisplayName() {
    let navigate = useNavigate();
    const { user } = useUserContext();

  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const displayName = formData.get('displayName');

    await setUserDisplayName(user.id, displayName);

    navigate('/home');
  };

  return (
    <div className="display-name">
    <div className="main">
        <div className="display-name-logo">
            <p>KTH-Forum</p>
        </div>
        <div className="display-name-container">
            <div className="display-name-message">
                <p>Choose your display name:</p>
            </div>
            <form onSubmit={handleSubmit}>
                <div className="display-name-row">
                    <input id="displayName" name="displayName" required className="display-name-input" />
                </div>
                <div className="display-name-row">
                    <button type="submit" className="display-name-button">Set Display Name</button>
                </div>
            </form>
        </div>
    </div>
</div>
  );
}

export default DisplayName;