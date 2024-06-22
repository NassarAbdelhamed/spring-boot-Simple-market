"use client";
import { useState } from 'react';
import axios from 'axios';
import { useAppContext } from '@/app/Context';
import { useRouter } from 'next/navigation';
import './singup.css'

export default function Login() {
  const [username, setUsername] = useState('');
  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const router=useRouter()
  const {jwt,setJwt}=useAppContext();

  const handleSubmit = async (event: { preventDefault: () => void; }) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/auth/register', {
        name,
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
    <div className='page'>
      <h1>Singup</h1>
      <form onSubmit={handleSubmit}  className="form">
        <div>
          <label>name:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
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
        <button type="submit">Sing up</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}
