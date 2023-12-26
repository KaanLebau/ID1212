import PropTypes from 'prop-types';
import '../../assets/styles/Navbar.css';

function Navbar({courses, onCourseSelect, handleLogout}) {
    return (
        <header className="header">
        <div className="logo-nav-container">
            <div className="nav-logo">
                KTH-Forum
            </div>
            <nav id="navbar">
                {courses.map((course) => (
                    <a key={course.id} onClick={() => onCourseSelect(course.id)} className="navbar-link">
                        {course.courseId + " - " + course.courseName}
                    </a>
                ))}
                <a key={"logout"} onClick={() => handleLogout()} className="logout">Logout</a>
            </nav>
        </div>
        </header>
    );
}

Navbar.propTypes = {
    courses: PropTypes.arrayOf(PropTypes.shape({
      id: PropTypes.number.isRequired,
      courseName: PropTypes.string.isRequired
    })).isRequired,
    onCourseSelect: PropTypes.func,
  };

export default Navbar;