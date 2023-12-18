import '../../assets/styles/Navbar.css';

function Navbar({ courses }) {
    return (
        <header className="header">
        <div className="logo-nav-container">
            <div className="nav-logo">
                KTH-Forum
            </div>
            <nav id="navbar">
                {courses.map((course) => (
                    <a key={course.id} href={`home#c${course.id}`} className="navbar-link">
                        {course.name}
                    </a>
                ))}
            </nav>
        </div>
        </header>
    );
}

export default Navbar;