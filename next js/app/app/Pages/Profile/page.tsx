'use client';
import { useEffect, useState } from "react";
import { useAppContext } from "@/app/Context";
import axios from "axios";
import Nav from "@/app/Components/nav";
import Admin from "@/app/Components/admin";
import './profile.css'

export default function Page() {
  const { jwt } = useAppContext();
  const [data, setData] = useState({
    username: '',
    name: '',
    role:''
  });
  const [isClient, setIsClient] = useState(false);

  useEffect(() => {
    setIsClient(true);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      if (jwt) {
        try {
          const response = await axios.get('http://localhost:8080/customer', {
            headers: {
              'Authorization': `Bearer ${jwt}`
            }
          });
          const copydate = {
            username: response.data.username,
            name: response.data.name,
            role: response.data.role
          };
          setData(copydate);
        } catch (err) {
          console.error('Error fetching data:', err);
        }
      }
    };

    if (jwt) {
      fetchData();
    }
  }, [jwt]);

  if (!isClient) {
    return null; 
  }

  return (
    <div className="profile">
      <Nav logout product/>
      <div className="info">
      {data.role==='ADMIN'?<Admin/>:''}
      {data.name}
      {data.username}
      </div>
      </div>
  );
}