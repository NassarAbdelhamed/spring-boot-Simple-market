import Link from "next/link"
import './nav.css'

type navprops={
    login?:boolean,
    singup?:boolean,
    logout?:boolean,
    profile?:boolean,
    product?:boolean
}

export default function Nav({login,singup,logout,profile,product}:navprops){
    return(
        <div className="nav">
            <h1>simple market</h1>
            <div className="links">
                {login&&<Link href={'/Pages/Login'}>Login</Link>}
                {singup&&<Link href={'/Pages/Singup'}>Singup</Link>}
                {profile&&<Link href={'/Pages/Profile'}>Profile</Link>}
                {product&&<Link href={'/Pages/Product'}>Product</Link>}
                {logout&&<Link href={'/'}>Logout</Link>}
            </div>
        </div>
    )
    
}