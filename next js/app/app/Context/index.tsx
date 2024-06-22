"use client";
import { Dispatch, SetStateAction, createContext, useContext, useState, useEffect } from "react";

type Context = {
  jwt: string;
  setJwt: Dispatch<SetStateAction<string>>;
};

const AppContext = createContext<Context>({
  jwt: "",
  setJwt: () => {}
});

export function AppWrapper({ children }: { children: React.ReactNode }) {
  const [jwt, setJwt] = useState<string>("");

  useEffect(() => {
    if (typeof window !== 'undefined') {
      const storedJwt = localStorage.getItem('jwt');
      if (storedJwt) {
        setJwt(storedJwt);
      }
    }
  }, []);

  useEffect(() => {
    if (typeof window !== 'undefined') {
      localStorage.setItem('jwt', jwt);
    }
  }, [jwt]);

  return (
    <AppContext.Provider value={{ jwt, setJwt }}>
      {children}
    </AppContext.Provider>
  );
}

export function useAppContext() {
  return useContext(AppContext);
}
