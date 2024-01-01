import React, { createContext, useContext, useState, useEffect } from 'react';
import { login } from './ApiService';

const UserContext = createContext(null);

export const useUserContext = () => useContext(UserContext);

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(JSON.parse(sessionStorage.getItem('user')));

  useEffect(() => {
    if (user) {
      sessionStorage.setItem('user', JSON.stringify(user));
    } else {
      sessionStorage.removeItem('user');
    }
  }, [user]);

  const loginUser = async (username, password) => {
    try {
      const user = await login(username, password);

      setUser(user);
      sessionStorage.setItem('user', JSON.stringify(user));
      return user;

    } catch (error) {
      console.error('Login failed:', error);
      return null;
    }
  };

  const logoutUser = () => {
    setUser(null);
  };

  const contextValue = {
    user,
    loginUser,
    logoutUser,
  };

  return (
    <UserContext.Provider value={contextValue}>
      {children}
    </UserContext.Provider>
  );
};