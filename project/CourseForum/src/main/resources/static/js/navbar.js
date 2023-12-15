document.addEventListener('DOMContentLoaded', function() {
    const navbar = document.getElementById('navbar');
    const courses = [{name:"Course 1", id: 1}, {name:'Course 2', id: 2}, {name:'Course 3', id: 3}]; //TODO: Ladda från databasen

    courses.forEach(course => {
        const link = document.createElement('a');
        link.href = `home#c${course.id}`;
        link.textContent = course.name;
        link.className = 'navbar-link';
        navbar.appendChild(link);
    });
});