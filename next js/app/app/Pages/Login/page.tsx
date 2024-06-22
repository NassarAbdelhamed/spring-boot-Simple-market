"use client";
import { useState } from 'react';
import axios from 'axios';
import { useAppContext } from '@/app/Context';
import { useRouter } from 'next/navigation';
import './login.css'

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const router=useRouter()
  const {jwt,setJwt}=useAppContext();

  const handleSubmit = async (event: { preventDefault: () => void; }) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/auth/authenticate', {
        username,
        password
      });
      setJwt(response.data.jwt);
      setError('');
      router.push('/Pages/Profile')
    } catch (err) {
      setError('Authentication failed. Please check your credentials.');
    }
  };

  return (
    <div className="page">
    <h1>Login</h1>
    <form onSubmit={handleSubmit} className="form">
      <div>
        <label>Email:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </div>
      <button type="submit">Login</button>
    </form>
    {error && <p style={{ color: 'red' }}>{error}</p>}
  </div>
  );
}
