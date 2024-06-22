'use client';
import { useEffect, useState } from "react";
import { useAppContext } from "@/app/Context";
import axios from "axios";
import Nav from "@/app/Components/nav";


export default function Page() {
  const { jwt } = useAppContext();
  const [data, setData] = useState([{
    "id":0 ,
    "name": "",
    "price":0 ,
    "description": "",
    "carts": [],
    "valid": true
  }]);
  const [isClient, setIsClient] = useState(false);

  useEffect(() => {
    setIsClient(true);
  }, []);

  useEffect(() => {
    const fetchData = async () => {
      if (jwt) {
        try {
          const response = await axios.get('http://localhost:8080/product', {
            headers: {
              'Authorization': `Bearer ${jwt}`
            }
          });
          setData(response.data);
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
    <div>
      <Nav profile logout/>
  {data.map((p) => (
  <div key={p.id} className="card">
    <ul>
    <li>{p.id}</li>
    <li>{p.name}</li>
    <li>{p.price}$</li>
    <li>{p.description}</li>
    <li>{p.valid?'found':'notValid'}</li>
    </ul>
    </div>
))}
    </div>
  );
}